package com.kaiyu.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.kaiyu.kyapicommon.model.entity.InterfaceInfo;
import com.kaiyu.kyapicommon.model.entity.UserInterfaceInfo;
import com.kaiyu.project.annotation.AuthCheck;
import com.kaiyu.project.common.BaseResponse;
import com.kaiyu.project.common.ErrorCode;
import com.kaiyu.project.common.ResultUtils;
import com.kaiyu.project.exception.BusinessException;
import com.kaiyu.project.mapper.UserInterfaceInfoMapper;
import com.kaiyu.project.model.vo.InterFaceInfoVO;
import com.kaiyu.project.service.InterfaceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 分许控制器
 */
@Slf4j
@RestController
@RequestMapping("/analysis")
public class AnalysisController {

    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @GetMapping("top/interface/invoke")
    @AuthCheck(mustRole = "admin")
    public BaseResponse<List<InterFaceInfoVO>> listTopInvokeInterfaceInfo(){
//todo 不懂
        List<UserInterfaceInfo> userInterfaceInfoList = userInterfaceInfoMapper.listTopInvokeInterfaceInfo(3);

        Map<Long, List<UserInterfaceInfo>> interfaceInfoIdObjMap = userInterfaceInfoList.stream()
                .collect(Collectors.groupingBy(UserInterfaceInfo::getInterfaceInfoId));
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", interfaceInfoIdObjMap.keySet());
        List<InterfaceInfo> list = interfaceInfoService.list(queryWrapper);

        if (CollectionUtils.isEmpty(list)){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }

        List<InterFaceInfoVO> interFaceInfoVOList = list.stream().map(interfaceInfo -> {
            InterFaceInfoVO interfaceInfoVO = new InterFaceInfoVO();
            BeanUtils.copyProperties(interfaceInfo, interfaceInfoVO);
            Integer totalNum = interfaceInfoIdObjMap.get(interfaceInfo.getId()).get(0).getTotalNum();
            interfaceInfoVO.setTotalNum(totalNum);
            return interfaceInfoVO;
        }).collect(Collectors.toList());

        return ResultUtils.success(interFaceInfoVOList);
    }
}
