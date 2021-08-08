
let Utils = {
    isOk: (response) => {
        return response.type === 'opaque' || response.ok
    }
}

export default Utils
