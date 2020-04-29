package com.qzl.lzshzz.app.jpa

import com.qzl.lzshzz.model.ucenter.SysAccount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface CloudServerRepository : JpaRepository<SysAccount, String>, JpaSpecificationExecutor<SysAccount>