package csp;

import org.hswebframework.ezorm.core.param.Term;
import org.hswebframework.ezorm.core.param.TermType;
import org.hswebframework.web.authorization.Permission;
import org.hswebframework.web.authorization.access.DataAccessConfig;
import org.hswebframework.web.authorization.access.DataAccessHandler;
import org.hswebframework.web.authorization.access.ScopeDataAccessConfig;
import org.hswebframework.web.authorization.define.AuthorizingContext;
import org.hswebframework.web.commons.entity.Entity;
import org.hswebframework.web.commons.entity.param.QueryParamEntity;
import org.hswebframework.web.organizational.authorization.access.OrgAttachEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class RoleOrgScopeDataAccessHandler implements DataAccessHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean isSupport(DataAccessConfig access) {
        return access instanceof ScopeDataAccessConfig && access.getType().equals("DEPARTMENT_SCOPE");
    }

    @Override
    public boolean handle(DataAccessConfig dataAccessConfig, AuthorizingContext authorizingContext) {
        // 获取roleId
        Set<String> roles = authorizingContext.getDefinition().getRoles();
        // 根据roleId获取对应角色
        Set<String> scope = getTryOperationScope();

        switch (dataAccessConfig.getAction()) {
            case Permission.ACTION_QUERY:
              return handleQuery(authorizingContext);
            default:
                return false;

        }
    }

    protected boolean handleQuery(AuthorizingContext context) {
        Entity entity = context.getParamContext().getParams()
                .values().stream()
                .filter(Entity.class::isInstance)
                .map(Entity.class::cast)
                .findAny().orElse(null);

        if (entity == null) {
            logger.warn("try validate query access, but query entity is null or not instance of org.hswebframework.web.commons.entity.Entity");
            return true;
        }
        Set<String> scope = getTryOperationScope();
        if (scope.isEmpty()) {
            logger.warn("try validate query access,but config is empty!");
            return true;
        }
        if (entity instanceof QueryParamEntity) {
            if (logger.isDebugEnabled()) {
                logger.debug("try rebuild query param ...");
            }
            //重构查询条件
            //如: 旧的条件为 where name =? or name = ?
            //重构后为: where org_id in(?,?) and (name = ? or name = ?)
            QueryParamEntity queryParamEntity = ((QueryParamEntity) entity);
            queryParamEntity.toNestQuery(query-> query.accept(createQueryTerm(scope, context)));
        } else {
            logger.warn("try validate query access,but entity not support, QueryParamEntity support now!");
        }
        return true;
    }
    protected Term createQueryTerm(Set<String> scope, AuthorizingContext authorizingContext) {
        Term term = new Term();
        term.setColumn(OrgAttachEntity.orgId);
        term.setTermType(TermType.in);
        term.setValue(scope);
        term.setType(Term.Type.and);
        return term;
    }
    // 获取对应人下的部门角色所对应的orgId
    protected Set<String> getTryOperationScope() {

        Set<String> orgs = new HashSet<>();
        orgs.add("1");
        orgs.add("2");
        orgs.add("3");

        return orgs;
    }

}
