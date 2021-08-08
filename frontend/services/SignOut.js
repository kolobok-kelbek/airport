import SignOut from "../http/SignOut";
import Utils from "../http/Utils";
import React from "react";
import routers from "../http/Routers";

export default function SingOut(router, indexContext) {
    SignOut(response => {
        if (Utils.isOk(response)) {
            indexContext.setUser(null)
            router.push(routers.SIGN_IN)
        }
    })
}