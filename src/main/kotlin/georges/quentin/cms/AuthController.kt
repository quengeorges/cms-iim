package georges.quentin.cms

import georges.quentin.cms.model.User

interface AuthController {
     fun login(mail: String?, password: String?)

    interface View {
        fun success(user: User)
        fun error()
    }
}