import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.velocip.io.userAccess
import com.velocip.io.userCount
import com.velocip.io.users
import io.ktor.application.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.features.json.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.jackson.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

var userData = "{\"users\": [\"Patryk\", \"Andrew\", \"Adrian\", \"Aiden\", ]}"


@KtorExperimentalLocationsAPI
@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(ContentNegotiation) {
        jackson {
            registerModule(JavaTimeModule())
            enable(SerializationFeature.INDENT_OUTPUT)
            disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

            // enable(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)
            // enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)
            // enable(SerializationFeature.WRAP_ROOT_VALUE)
        }
    }

    install(Locations){

    }



    val client: HttpClient = HttpClient(Apache) {
        install(JsonFeature){
            serializer = GsonSerializer()
        }
    }


    routing {
        this.apply {
            trace { application.log.trace(it.buildText()) }
            event(client)
            root()
            rootPost()
            users()
            userCount()
            userAccess()
            swimmingEvent()
            swimEvent()
            userLocation()
        }
    }


}
