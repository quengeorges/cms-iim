package georges.quentin.cms.control

import georges.quentin.cms.AuthController
import georges.quentin.cms.Model
import georges.quentin.cms.model.User

class AuthControllerImpl(val model: Model, val view: AuthController.View): AuthController {
    override fun login(mail: String?, password: String?) {
        if (mail != null && password != null) {
            val user: User? = model.getUser(mail, password)

            if (user != null) {
                view.success(user)
            } else {
                view.error()
            }
        } else {
            view.error()
        }
    }
}