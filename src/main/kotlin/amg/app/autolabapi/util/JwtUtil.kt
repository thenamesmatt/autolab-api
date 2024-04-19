package amg.app.autolabapi.util

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.security.SignatureException
import java.util.Date

object JwtUtil {
    private const val SECRET_KEY = "XaTzIuXiGvEh81orlxx6I+PpOne7lFL10+PEpNkbefM="  // Make sure to keep this safe and inject it from a secure source
    private const val EXPIRATION_TIME = 2_592_000_000 // 30 days in milliseconds

    fun generateToken(username: String): String {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact()
    }

    fun validateToken(token: String): Boolean {
        try {
            val claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .body
            return !claims.expiration.before(Date())
        } catch (e: SignatureException) {
            // Log and handle invalid JWT signature
            return false
        } catch (e: Exception) {
            // Log and handle other exceptions such as malformed token or expired token
            return false
        }
    }

    fun getUsernameFromToken(token: String): String? {
        return try {
            val claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .body
            claims.subject
        } catch (e: Exception) {
            null
        }
    }
}
