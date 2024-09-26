import io.ktor.client.*
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking

fun main() = runBlocking{
    /*val client: HttpClient = HttpClient(CIO) {
        defaultRequest {
            url("http://api.kanye.rest/")
        }
        install(Logging)
    }
    val response: HttpResponse = client.get("/")
    println(response.bodyAsText())*/
}

