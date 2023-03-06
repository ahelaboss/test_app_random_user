package com.yourgains.testapprandomuser.data.repository

import com.yourgains.testapprandomuser.data.PendingResult
import com.yourgains.testapprandomuser.data.entity.db.UserDB
import com.yourgains.testapprandomuser.data.entity.mapper.DBToUIMapper
import com.yourgains.testapprandomuser.data.entity.mapper.Mapper
import com.yourgains.testapprandomuser.data.entity.mapper.NetworkToUIMapper
import com.yourgains.testapprandomuser.data.entity.network.response.UserListResponse
import com.yourgains.testapprandomuser.data.entity.presentation.UserUI
import com.yourgains.testapprandomuser.data.network.service.UserService
import com.yourgains.testapprandomuser.data.storage.db.dao.IUserDao
import com.yourgains.testapprandomuser.domain.repository.IUserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userService: UserService,
    private val userDao: IUserDao
) : BaseRepositoryImpl(), IUserRepository {

    private val userMapper = object : Mapper<UserListResponse, List<UserUI>> {
        override suspend fun map(data: UserListResponse): List<UserUI> =
            data.results?.map { NetworkToUIMapper.map(it) } ?: emptyList()
    }

    private val userDBMapper = object : Mapper<List<UserDB>, List<UserUI>> {
        override suspend fun map(data: List<UserDB>): List<UserUI> =
            data.map { DBToUIMapper.map(it) }
    }

    override suspend fun getUsers(count: Int): PendingResult<List<UserUI>> =
        request(userService.getUsers(count), userMapper)

    override suspend fun saveHistory(users: List<UserDB>) {
        db(unitMapper) { userDao.insert(users) }
    }

    override suspend fun getHistory(): PendingResult<List<UserUI>> = db(userDBMapper) { userDao.select() }

}