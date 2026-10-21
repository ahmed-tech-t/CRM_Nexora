package com.example.crm_nexora.data.remote.interceptor

private const val TAG = "TokenAuthenticator"
//
//@Singleton
//class TokenAuthenticator @Inject constructor(
//    private val authSessionManager: AuthSessionManager,
//    @RefreshClient private val authService: AuthService,
//) : Authenticator {
//    init {
//        Log.d(TAG, ": token authenticator interceptor initialized")
//    }
//
//    private val mutex = Mutex()
//    override fun authenticate(route: Route?, response: Response): Request? {
//        if (response.code != 401) return null
//        if (responseCount(response) >= 2) return null
//        if (response.request.url.encodedPath.contains("refresh-token")) return null
//        val failedAccess = authSessionManager.getAccessTokenSync()
//        // Guest / no access token → no refresh
//        if (failedAccess.isBlank()) return null
//        return runBlocking {
//            mutex.withLock {
//                // Parallel 401 may have already refreshed
//                val latestAccess = authSessionManager.getAccessTokenSync()
//                if (latestAccess.isNotBlank() && latestAccess != failedAccess) {
//                    return@withLock retryWithBearer(response, latestAccess)
//                }
//                val refreshToken = authSessionManager.getRefreshTokenSync()
//                if (refreshToken.isBlank()) {
//                    authSessionManager.onSessionExpired()
//                    return@withLock null
//                }
//                when (val result = refreshTokens(refreshToken)) {
//                    is RefreshResult.Success -> {
//                        authSessionManager.saveSession(
//                            result.tokens.accessToken,
//                            result.tokens.refreshToken,
//                        )
//                        retryWithBearer(response, result.tokens.accessToken)
//                    }
//
//                    RefreshResult.InvalidSession -> {
//                        authSessionManager.onSessionExpired()
//                        null
//                    }
//
//                    RefreshResult.TransientFailure -> null
//                }
//            }
//        }
//    }
//
//    private suspend fun refreshTokens(refreshToken: String): RefreshResult {
//        return try {
//            val body = authService.refreshToken(RefreshTokenRequest(refreshToken))
//            val data = body.data
//            if (
//                body.status &&
//                data != null &&
//                data.accessToken.isNotBlank() &&
//                data.refreshToken.isNotBlank()
//            ) {
//                RefreshResult.Success(
//                    TokenPair(
//                        accessToken = data.accessToken,
//                        refreshToken = data.refreshToken,
//                    )
//                )
//            } else {
//                RefreshResult.InvalidSession
//            }
//        } catch (e: HttpException) {
//            when (e.code()) {
//                401, 403 -> RefreshResult.InvalidSession
//                else -> RefreshResult.TransientFailure
//            }
//        } catch (_: IOException) {
//            RefreshResult.TransientFailure
//        } catch (_: Exception) {
//            RefreshResult.TransientFailure
//        }
//    }
//
//    private fun retryWithBearer(response: Response, accessToken: String): Request {
//        return response.request.newBuilder()
//            .header("Authorization", "Bearer $accessToken")
//            .removeHeader("X-Guest-Token")
//            .build()
//    }
//
//    private fun responseCount(response: Response): Int {
//        var count = 1
//        var prior = response.priorResponse
//        while (prior != null) {
//            count++
//            prior = prior.priorResponse
//        }
//        return count
//    }
//
//    private sealed class RefreshResult {
//        data class Success(val tokens: TokenPair) : RefreshResult()
//        data object InvalidSession : RefreshResult()
//        data object TransientFailure : RefreshResult()
//    }
//
//    private data class TokenPair(
//        val accessToken: String,
//        val refreshToken: String,
//    )
//}
