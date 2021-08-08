import apiRouters from "./ApiRouters";
import Fetcher from "./Fetcher"
import React from "react";

export default function GetCurrentUser (dataHandler) {
    Fetcher
        .get()
        .path(apiRouters.GET_CURRENT_USER)
        .dataHandler(dataHandler)
        .fetch()
};
