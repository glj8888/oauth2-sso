package com.glj.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glj.entity.SysRolePo;
import com.glj.entity.SysUserPo;
import com.glj.mapper.SysUserMapper;
import com.glj.service.ISysUserRoleService;
import com.glj.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author gaoleijie
 * @since 2019-01-11
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserPo> implements ISysUserService {
    @Autowired
    private ISysUserRoleService userRoleService;

    @Override
    public SysUserPo getUserByUserCode(String userName) {
        QueryWrapper<SysUserPo> wrapper = new QueryWrapper<>();
        wrapper.eq(SysUserPo.USERNAME, userName);
        SysUserPo userPo = this.baseMapper.selectOne(wrapper);
        if (null != userPo) {
            List<SysRolePo> rolePos = userRoleService.getRolesByUserId(userPo.getId());
            userPo.setRolePoList(rolePos);
        }
        return userPo;
    }
}
