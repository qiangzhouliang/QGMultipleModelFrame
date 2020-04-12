package com.zdww.lzshzz.app.jpa

import com.zdww.lzshzz.model.ucenter.SysAccount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface CloudServerRepository : JpaRepository<SysAccount, String>, JpaSpecificationExecutor<SysAccount>