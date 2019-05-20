package georges.quentin.cms

import java.sql.Connection
import java.sql.DriverManager
import java.util.concurrent.ConcurrentLinkedQueue

class ConnectionPool(val url: String, val user: String, val pass: String) {
    private val queue = ConcurrentLinkedQueue<Connection>()

    fun getConnection(): Connection {
        val connection = queue.poll()

        if (connection == null) {
            return DriverManager.getConnection(url, user, pass)
        }
        else {
            return connection
        }
    }

    fun releaseConnection(c: Connection) {
        queue.add(c)
    }

    inline fun useConnection(f: (Connection) -> Unit) {
        val connection = getConnection()
        try {
            f(connection)
        } finally {
            releaseConnection(connection)
        }
    }
}