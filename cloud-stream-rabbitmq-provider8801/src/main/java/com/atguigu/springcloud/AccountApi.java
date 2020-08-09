package com.bitsun.bopaf.bcs.uc.api;

import com.bitsun.bopaf.bcs.uc.dto.req.*;
import com.bitsun.bopaf.bcs.uc.dto.res.*;
import com.bitsun.bopaf.bcs.uc.dto.req.*;
import com.bitsun.bopaf.bcs.uc.dto.res.*;
import com.bitsun.core.common.persistence.Pager;
import com.bitsun.core.common.web.ResultDTO;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 账户
 */
@Api(value = "account", description = "账号接口", tags = {"account"})
public interface AccountApi {

    /**
     * 根据账号（accountId）和租户id(tenantId)查询跟账号相关的账户，登陆租户下的员工等信息
     *
     * @return 返回账号信息
     */
    @ApiOperation(value = "根据账号（accountId）和租户id(tenantId)查询跟账号相关的账户，登陆租户下的员工等信息", tags = {
            "account",}, nickname = "getAccountPersonDetail")
    @ApiImplicitParams({@ApiImplicitParam(name = "accountId", value = "账号id", paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "tenantId", value = "租户id", paramType = "query", dataType = "long")
    })
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    ResultDTO<AccountPersonDetailResDto> getAccountPersonDetail(Long accountId, Long tenantId);

    /**
     * 冻结帐号
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "冻结帐号", tags = {"account"}, nickname = "freeze")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "冻结是否成功,000:成功，否则失败")})
    ResultDTO<AccountResDto> freezeAccount(Long id, UpdateStatusOpinionReqDto reqDto);

    /**
     * 解冻帐号
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "解冻帐号", tags = {"account"}, nickname = "freeze")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "解冻是否成功,000:成功，否则失败")})
    ResultDTO<AccountResDto> defreezeAccount(Long id, UpdateStatusOpinionReqDto reqDto);

    /**
     * 注销账号
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "注销帐号", tags = {"account"}, nickname = "freeze")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "注销是否成功,000:成功，否则失败")})
    ResultDTO<AccountResDto> destroyAccount(Long id, UpdateStatusOpinionReqDto reqDto);


    /*------------------------------------------------------------------------------------------------------------*/

    /**
     * 普通帐号登录
     *
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "普通帐号登录", tags = {"account"}, nickname = "login")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "登录是否成功,000:成功，否则失败")})
    ResultDTO<LoginResDto> login(LoginReqDto reqDto);

    /**
     * 根据id查询账号
     *
     * @param id, reqDto
     * @return 返回完整的资源对象
     */
    @ApiOperation(value = "根据id查询账号", tags = {"account"}, nickname = "getAccount")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000:成功，否则失败")})
    ResultDTO<AccountResDto> getAccount(@ApiParam(value = "对象ID", required = true) @PathVariable("id") Long id);

    /**
     * 根据账号id(accountId)添加登录身份
     *
     * @param accountId
     * @return
     */
    @ApiOperation(value = "根据账号id(accountId)添加登录身份", tags = {"account"}, nickname = "addIdentify")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "添加是否成功,000:成功，否则失败")})
    ResultDTO<Boolean> addIdentify(Long accountId, IdentifyMetaReqDto reqDto);

    /**
     * 根据身份id修改登录身份
     *
     * @param identifyId, reqDto
     * @return
     */
    @ApiOperation(value = "根据身份id修改登录身份", tags = {"account"}, nickname = "updateIdentify")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "修改是否成功,000:成功，否则失败")})
    ResultDTO<Boolean> updateIdentify(Long identifyId, IdentifyMetaReqDto reqDto);

    /**
     * 根据身份id删除登录身份
     *
     * @param identifyId
     * @return
     */
    @ApiOperation(value = "根据身份id删除登录身份", tags = {"account"}, nickname = "deleteIdentify")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "注销是否成功,000:成功，否则失败")})
    ResultDTO<Boolean> deleteIdentify(Long identifyId);


    /**
     * 根据身份信息查询账号
     *
     * @return 返回账号
     */
    @ApiOperation(value = "根据身份信息查询一个账号", tags = {
            "account",}, nickname = "getAccountByIdentify")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    ResultDTO<AccountResDto> getAccountByIdentify(String identify);

    /**
     * 根据身份类型查询身份列表
     *
     * @return 返回身份
     */
    @ApiOperation(value = "根据身份类型分页查询身份", nickname = "getIdentifyListByType")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    ResultDTO<Pager<IdentifyResDto>> getIdentifyListByType(Short identityType,
                                                           String identity, Integer currentPage, Integer pageSize);

    /**
     * 批量加入租户
     * @param id
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "批量加入租户",tags = "tenant",nickname = "addToTenantBatch")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    ResultDTO<Boolean> addToTenantBatch(Long id, BatchAddTenantReqDto reqDto);

    /**
     * 添加一个账号，同时添加身份信息返回新建的账号信息
     *
     * @return 返回新创建的资源对象
     */
    @ApiOperation(value = "添加一个账号，同时添加身份信息返回新建的账号信息", tags = {"account"}, nickname = "addAccount")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "000000:成功，否则失败")})
    ResultDTO<AccountResDto> addAccount(AccountReqDto reqDto);

    /**
     * 分页多条件查询账号信息
     *
     * @param params 参数params
     * @return 返回一系列资源对象
     */
    @ApiOperation(value = "分页多条件查询账号信息(qp-参数支持的操作符号有: eq(=),ne(!=),gt(>),lt(<),ge(>=),le(<=),in,like,notLike,likeleft(左边LIKE '%xxx'),likeright(右边LIKE 'xx%'))", tags = {
            "account",}, nickname = "getAccountList")
    @ApiImplicitParams({@ApiImplicitParam(name = "currentPage", value = "当前页数", paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "sorter", value = "排序条件 desc-字段名或者asc-字段名", paramType = "query"),
            @ApiImplicitParam(name = "qp-username-like", value = "账号", paramType = "query"),
            @ApiImplicitParam(name = "qp-name-like", value = "员工名称", paramType = "query"),
            @ApiImplicitParam(name = "qp-mobile-like", value = "员工手机号", paramType = "query"),
            @ApiImplicitParam(name = "qp-tenantId-eq", value = "租户id", paramType = "query"),
            @ApiImplicitParam(name = "qp-status-eq", value = "状态 1-正常  11-停用  101-冻结", paramType = "query"),
            @ApiImplicitParam(name = "identity",value = "账号或手机号",paramType = "query"),
            @ApiImplicitParam(name = "tenantName",value = "账号归属租户名称",paramType = "query")
    })
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    ResultDTO<Pager<AccountResDto>> getAccountList(Map<String, Object> params);

    /**
     * 根据人员id（personId）不分页查询账号基本信息
     *
     * @return 返回账号列表
     */
    @ApiOperation(value = "根据人员id（personId）不分页查询账号基本信息", tags = {
            "account",}, nickname = "getAccountListByPersonId")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    ResultDTO<List<AccountResDto>> getAccountListByPersonId(Long personId);

    /**
     * 根据id部分更新账号,更新对象里有值的属性,空值不更新(同时更新身份信息，超级管理员不能更改，没有重复校验)
     *
     * @param id, reqDto
     * @return 返回完整的资源对象
     */
    @ApiOperation(value = "根据id部分更新账号,更新对象里有值的属性,空值不更新(同时更新身份信息，超级管理员不能更改，没有重复校验)", tags = {"account"}, nickname = "patchUpdateAccount")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000:成功，否则失败")})
    ResultDTO<Boolean> patchUpdateAccount(Long id, AccountUpdateReqDto reqDto);


    /**
     * 根据账号id列表不分页查询账号基本信息
     *
     * @return 返回账号列表
     */
    @ApiOperation(value = "根据账号id列表不分页查询账号基本信息", tags = {
            "account",}, nickname = "getAccountList")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    ResultDTO<List<AccountResDto>> getAccountListByIds(String accountIds);

    /**
     * 根据账号id修改密码
     *
     * @param reqDto 密码信息
     * @return
     */
    @ApiOperation(value = "根据账号id修改密码", tags = {
            "account",}, nickname = "changePwd")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    ResultDTO<AccountResDto> changePwd(Long id, NewPwdReqDto reqDto);

    /**
     * 根据身份修改密码
     *
     * @param reqDto 密码信息
     * @return
     */
    @ApiOperation(value = "通过身份信息（手机号码，邮箱）修改账号密码", tags = {
            "account",}, nickname = "changePwdByIdentify")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    ResultDTO<AccountResDto> changePwdByIdentify(String identify, ResetPwdReqDto reqDto);

    /**
     * 平台端重置密码
     * @param reqDto 密码信息
     */
    @ApiOperation(value = "平台端重置密码", tags = {"account",}, nickname = "changePwdByAccountId")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    ResultDTO<AccountResDto> changePwdByAccountId(ResetPwdReqDto reqDto);

    /**
     * 点击重置密码根据账号直接修改
     * @param accountId 账号id
     * @return 修改后的账户信息
     */
    @ApiOperation(value = "通过身份信息（手机号码，邮箱）", tags = {"account",}, nickname = "directPwdByIAccountId")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    ResultDTO<Boolean> directResetPwdByIAccountId(Long accountId);



    /**
     * 根据personId不分页获取账号信息
     *
     * @return
     */
    @ApiOperation(value = "根据personId不分页获取账号信息", tags = {"account"}, nickname = "selectAccountByPersonId")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功 000000 成功 否则失败")})
    ResultDTO<List<AccountResDto>> selectAccountByPersonId(Long personId);

    /**
     * 根据personId列表不分页获取账号信息
     *
     * @return
     */
    @ApiOperation(value = "根据personId列表不分页获取账号信息", tags = {"account"}, nickname = "selectAccountByPersonId")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功 000000 成功 否则失败")})
    ResultDTO<List<AccountResDto>> selectAccountByPersonIds(String personId);

    /**
     * 根据accountId 和 tenantId 获取管理员信息
     *
     * @return
     */
    @ApiOperation(value = "根据accountId 和 tenantId 获取管理员信息", tags = {"account"}, nickname = "selectAccountByAccountIdAndTenantId")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功 000000 成功 否则失败")})
    ResultDTO<UserInfoResDto> selectAccountByAccountIdAndTenantId(Long personId, Long tenantId);


    /**
     * 激活一个或多个账号
     *
     * @param ids 英文逗号隔开
     * @return 返沪一个空文档
     */
    @ApiOperation(value = "账号启用,修改一个或多个账号状态", tags = {"account"}, nickname = "startAccount")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    ResultDTO<Boolean> startAccount(String ids,UpdateStatusOpinionReqDto reqDto);

    /**
     * 账号禁用,修改一个或多个账号状态
     *
     * @param ids 英文逗号隔开
     * @return 返沪一个空文档
     */
    @ApiOperation(value = "账号禁用,修改一个或多个账号状态", tags = {"account"}, nickname = "stopAccount")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    ResultDTO<Boolean> stopAccount(String ids,UpdateStatusOpinionReqDto reqDto);


    /**
     * 表格批量导入账号（同时创建身份）
     * @param file
     * @return
     */
    @ApiOperation(value = "表格批量导入账号（同时创建身份）", tags = {"account"}, nickname = "importAccount")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    ResultDTO<List<FailAccountResDto>> importAccount(MultipartFile file) throws IOException;

    /**
     * 根据tenantId部分页查账户信息
     * @param tenantId tenantId
     * @return 返回一系列资源对象
     */
    @ApiOperation(value = "根据tenantId部分页查账户信息", tags = {"account",}, nickname = "getPersonEmployeePagerByOrg")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    ResultDTO<List<AccountResDto>> getAccountListByTid(String tenantId);

    /**
     * 小程序注册(创建person、account、identify、thirdpartaccount)
     *
     * @return 返回新创建的资源对象
     */
    @ApiOperation(value = "小程序注册(创建person、account、identify、thirdpartaccount)", tags = {"account"}, nickname = "wxRegister")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "000000:成功，否则失败")})
    ResultDTO<WxRegisterInfoResDto> wxRegister(WxRegisterInfoReqDto reqDto);

    /**
     * 根据企业微信id查询用户基本信息
     *
     * @return 返回新创建的资源对象
     */
    @ApiOperation(value = "根据企业微信id查询用户基本信息", tags = {"account"}, nickname = "getUserBaseInfoByWorkWeChat")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "workWeChatId", value = "企业微信id", paramType = "query"),
    })
    @ApiResponses(value = {@ApiResponse(code = 200, message = "000000:成功，否则失败")})
    ResultDTO<AccountPersonDetailResDto> getUserBaseInfoByWorkWeChat(String workWeChatId);
    /**
     * 多条件分页查询账号信息
     *
     * @param params 参数params
     * @return 返回一系列资源对象
     */
    @ApiOperation(value = "多条件分页查询账号信息", tags = {
            "account",}, nickname = "getAccountList")
    @ApiImplicitParams({@ApiImplicitParam(name = "currentPage", value = "当前页数", paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "sorter", value = "排序条件 desc-字段名或者asc-字段名", paramType = "query"),
            @ApiImplicitParam(name = "qp-personId-eq", value = "", paramType = "query"),
            @ApiImplicitParam(name = "qp-regType-eq", value = "账号类型：1-员工  2-公司 3-C端用户", paramType = "query"),
            @ApiImplicitParam(name = "qp-acType-eq", value = "鉴权类型: 1-本地 2-第三方授权", paramType = "query"),
            @ApiImplicitParam(name = "qp-status-eq", value = "账号状态：1-正常  11-停用", paramType = "query"),
            @ApiImplicitParam(name = "qp-name-eq", value = "名称", paramType = "query"),
            @ApiImplicitParam(name = "qp-username-eq", value = "登录名", paramType = "query"),
            @ApiImplicitParam(name = "qp-password-eq", value = "密码", paramType = "query"),
            @ApiImplicitParam(name = "qp-salt-eq", value = "盐", paramType = "query"),
            @ApiImplicitParam(name = "qp-pwinvaldate-eq", value = "失效日期", paramType = "query"),
            @ApiImplicitParam(name = "qp-errcount-eq", value = "密码错误次数", paramType = "query"),
            @ApiImplicitParam(name = "qp-nickname-eq", value = "昵称", paramType = "query"),
            @ApiImplicitParam(name = "qp-imgUrl-eq", value = "头像", paramType = "query"),
            @ApiImplicitParam(name = "qp-phone-eq", value = "手机", paramType = "query"),
            @ApiImplicitParam(name = "qp-email-eq", value = "邮箱", paramType = "query"),
            @ApiImplicitParam(name = "qp-lastlogin-eq", value = "上次登陆时间", paramType = "query"),
            @ApiImplicitParam(name = "qp-extention-eq", value = "扩展字段", paramType = "query"),
            @ApiImplicitParam(name = "qp-lableIds-eq", value = "标签", paramType = "query"),
            @ApiImplicitParam(name = "qp-recommendId-eq", value = "推荐人", paramType = "query"),
            @ApiImplicitParam(name = "qp-recommendCode-eq", value = "推荐码", paramType = "query"),
            @ApiImplicitParam(name = "qp-createUserId-eq", value = "", paramType = "query"),
            @ApiImplicitParam(name = "qp-createUserName-eq", value = "", paramType = "query"),
            @ApiImplicitParam(name = "qp-createTime-eq", value = "", paramType = "query"),
            @ApiImplicitParam(name = "qp-modifyUserId-eq", value = "", paramType = "query"),
            @ApiImplicitParam(name = "qp-modifyUserName-eq", value = "", paramType = "query"),
            @ApiImplicitParam(name = "qp-modifyTime-eq", value = "", paramType = "query"),
            @ApiImplicitParam(name = "qp-bg-eq", value = "业务数据隔离", paramType = "query"),

    })
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    ResultDTO<Pager<AccountResDto>> getAccountPager(Map<String, Object> params);


    /**
     * 根据accountId查询用户和账号信息
     *
     * @return 返回新创建的资源对象
     */
    @ApiOperation(value = "根据accountId查询用户和账号信息", tags = {"account"}, nickname = "addOpsAccount")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "000000:成功，否则失败")})
    ResultDTO<PersonResDto> getAccountAndPersonInfo(Long accountId);

    /**
     * 只更新用户信息（person）
     *
     * @return 返回新创建的资源对象
     */
    @ApiOperation(value = "只更新用户信息（person）", tags = {"account"}, nickname = "addOpsAccount")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "000000:成功，否则失败")})
    ResultDTO<PersonResDto> updateUserInfo(UserReqDto userReqDto);

    /**
     * 分页查询组织下员工
     *
     * @return 返回新创建的资源对象
     */
    @ApiOperation(value = "分页查询组织下员工", tags = {"account"}, nickname = "addOpsAccount")
    @ApiImplicitParams({@ApiImplicitParam(name = "currentPage", value = "当前页数", paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "sorter", value = "排序条件 desc-字段名或者asc-字段名", paramType = "query"),
            @ApiImplicitParam(name = "qp-orgId-eq", value = "组织id", paramType = "query"),

    })
    @ApiResponses(value = {@ApiResponse(code = 200, message = "000000:成功，否则失败")})
    ResultDTO<Pager<AccountResDto>> getAccountByorg(Map<String,Object> param);

    /**
     * PC端根据手机号注册登陆一体
     *
     * @return 返回新创建的资源对象
     */
    @ApiOperation(value = "PC端根据手机号注册登陆一体", tags = {"account"}, nickname = "getUserResDtoByPhone")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "鉴权类型", paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "identity", value = "排序条件 desc-字段名或者asc-字段名", paramType = "query"),
    })
    @ApiResponses(value = {@ApiResponse(code = 200, message = "000000:成功，否则失败")})
    ResultDTO<AccountPersonDetailResDto> getUserResDtoByPhone(Map<String,Object> param);

    /**
     * 后台应用登陆
     * @param loginReqDto
     * @return
     */
    @ApiOperation(value = "后台应用登陆", tags = {"account"}, nickname = "loginPlatform")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "000000:成功，否则失败")})
    ResultDTO<AccountPersonDetailResDto> loginPC(@RequestBody LoginReqDto loginReqDto);


    /**
     * 多条件查询账号信息（只有account）
     * @param param
     * @return
     */
    @ApiOperation(value = "多条件查询账号信息（只有account）", tags = {"account"}, nickname = "getAccountListByParam")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "qp-accountCode-in", value = "codelist", paramType = "query")
    })
    @ApiResponses(value = {@ApiResponse(code = 200, message = "000000:成功，否则失败")})
    ResultDTO<List<AccountResDto>> getAccountListByParam(Map<String,Object> param);


    /**
     * 编辑员工的账号信息，同时更新指定的员工组织关联信息
     *
     * @return 返回新创建的资源对象
     */
    @ApiOperation(value = "编辑员工的账号信息，同时更新指定的员工组织关联信息", tags = {"account"}, nickname = "addOpsAccount")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "000000:成功，否则失败")})
    ResultDTO<AccountResDto> updateAccount(Long id,AccountReqDto reqDto);


    /**
     * 账号批量打标
     *
     * @param tagFlag true追加，false删除,accountReqDtos
     * @return 返回完整的资源对象
     */
    @ApiOperation(value = "账号批量打标(true追加，false删除)", tags = {"account"}, nickname = "patchUpdateAccount")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000:成功，否则失败")})
    ResultDTO<Boolean> updateTag(boolean tagFlag,List<AccountReqDto> accountReqDtos);


    /**
     * 条件查询一个账号
     *
     * @param params 信息
     * @return
     */
    @ApiOperation(value = "条件查询一个账号", tags = {"account",}, nickname = "getOneByCondition")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", paramType = "query"),
    })
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    ResultDTO<AccountResDto> getOneByCondition(Map<String,Object> params);

}
