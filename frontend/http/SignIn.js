import apiRouters from "./ApiRouters";
import Fetcher from "./Fetcher"

export default function SignIn(username, password, responseHandler) {
    Fetcher
        .post()
        .path(apiRouters.SIGN_IN)
        .body({
            username: username,
            password: password,
        })
        .init({
            mode: "no-cors",
            credentials: "include",
        })
        .responseHandler(responseHandler)
        .fetch()
};
