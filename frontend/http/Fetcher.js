import Utils from "./Utils";

const apiDomain = "api.airport.local"
const port = 80
const protocol = "http"

const METHODS = {
    GET: "GET",
    POST: "POST",
    PUT: "PUT",
    PATCH: "PATCH",
    DELETE: "DELETE",
}

let createUrl = (path) => {
    return `${protocol}://${apiDomain}:${port}/${path}`
}

let Fetch = (method, path, body = null, query = null, init = null, responseHandler = null, dataHandler = null, errorHandler = null) => {
    let url = new URL(createUrl(path))

    if (query != null) {
        Object.keys(query).forEach(key => url.searchParams.append(key, query[key]))
    }

    if (responseHandler == null) {
        responseHandler = response => response.json()
    }

    if (errorHandler == null) {
        errorHandler = error => {
            console.log(error)
        }
    }

    let defaultInit = {
        method: method,
        mode: "cors",
        headers: {
            "Content-Type": "application/json",
        },
        credentials: "include",
        body: body === null ? null : JSON.stringify(body),
    }

    fetch(url, init === null ? defaultInit : Object.assign(defaultInit, init))
        .then(responseHandler)
        .then(dataHandler)
        .catch(errorHandler);
}

class Fetcher
{
    #method = METHODS.GET;
    #path = "";
    #body = null;
    #query = null;
    #init = null;
    #responseHandler = null;
    #dataHandler = null;
    #errorHandler = null;

    method = (method) => {
        this.#method = method
    }

    get = () => {
        this.method(METHODS.GET)

        return this
    }

    post = () => {
        this.method(METHODS.POST)

        return this
    }

    put = () => {
        this.method(METHODS.PUT)

        return this
    }

    patch = () => {
        this.method(METHODS.PATCH)

        return this
    }

    delete = () => {
        this.method(METHODS.DELETE)

        return this
    }

    path = (path) => {
        this.#path = path

        return this
    }

    body = (body) => {
        this.#body = body

        return this
    }

    query = (query) => {
        this.#query = query

        return this
    }

    init = (init) => {
        this.#init = init

        return this
    }

    responseHandler = (responseHandler) => {
        this.#responseHandler = responseHandler

        return this
    }

    dataHandler = (dataHandler) => {
        this.#dataHandler = dataHandler

        return this
    }

    errorHandler = (errorHandler) => {
        this.#errorHandler = errorHandler

        return this
    }

    fetch = () => {
        Fetch(this.#method, this.#path, this.#body, this.#query, this.#init, this.#responseHandler, this.#dataHandler, this.#errorHandler)
    }
}

export default new Fetcher()

export {Fetch, METHODS}
