package ar.com.eldars.transporte

class API {

    companion object{
        fun login(user : String , pass : String , callback: (request : Boolean) -> Unit){
            if (user == "admin" && pass == "12345"){
                callback( true)
            } else {
                callback( false)
            }
        }
    }
}