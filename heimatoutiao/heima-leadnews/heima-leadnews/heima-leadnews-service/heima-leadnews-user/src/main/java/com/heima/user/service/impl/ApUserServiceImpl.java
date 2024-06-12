package com.heima.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.user.dto.LoginDto;
import com.heima.model.user.pojos.ApUser;
import com.heima.user.mapper.ApUserMapper;
import com.heima.user.service.ApUserService;
import com.heima.utils.common.AppJwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class ApUserServiceImpl extends ServiceImpl<ApUserMapper, ApUser> implements ApUserService {
    @Override
    public ResponseResult login(LoginDto dto) {

            //1.正常用户登录 效验
            if (!StringUtils.isBlank(dto.getPhone()) && !StringUtils.isBlank(dto.getPassword())) {
               //1.根据手机号查询用户
                ApUser apUser = getOne(new LambdaQueryWrapper<ApUser>().eq(ApUser::getPhone, dto.getPhone()));
                //2.判断用户是否存在
                if (apUser == null) {
                    return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST,"用户不存在");
                }
            //查询密码
                String pswd = dto.getPassword();
                String salt = apUser.getSalt();
                pswd = DigestUtils.md5DigestAsHex((pswd + salt).getBytes());
                if (!pswd.equals(apUser.getPassword())) {//效验用户密码
                    return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
                }
                //3.生成token  jwt
                Map<String, Object> map = new HashMap<>();
                map.put("token", AppJwtUtil.getToken(apUser.getId().longValue()));
                apUser.setSalt("");
                apUser.setPassword("");
                map.put("user", apUser);
                return ResponseResult.okResult(map);
            } else {
                //2.游客  同样返回token  id = 0
                Map<String, Object> map = new HashMap<>();
                map.put("token", AppJwtUtil.getToken(0l));
                return ResponseResult.okResult(map);
            }
    }
}
