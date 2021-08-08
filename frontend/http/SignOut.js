import apiRouters from "./ApiRouters";
import Fetcher from "./Fetcher"

export default function SignOut(responseHandler) {
    Fetcher
        .post()
        .path(apiRouters.SIGN_OUT)
        .init({
            mode: "no-cors",
            credentials: "include",
        })
        .responseHandler(responseHandler)
        .fetch()
};
