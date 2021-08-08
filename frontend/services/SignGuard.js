import {useRouter} from "next/router";
import React from "react";
import IndexContext from "../context";

export default function SignGuard() {
    const router = useRouter();
    const indexContext = React.useContext(IndexContext)
    React.useEffect(() => {
        if (indexContext.user !== null) {
            router.push('/')
        }
    }, []);
}