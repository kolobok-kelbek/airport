import React from 'react'
import '../styles/globals.css'
import 'fontsource-roboto';
import Head from 'next/head';
import {IndexProvider} from '../context/index'
import Loader from "../components/Loader";
import {useRouter} from "next/router";

export default function MyApp({Component, pageProps}) {
    const [isLoaded, setIsLoaded] = React.useState(false);
    const router = useRouter();

    React.useEffect(() => {
        const handleStart = () => { setIsLoaded(true); };
        const handleComplete = () => { setIsLoaded(false); };

        router.events.on('routeChangeStart', handleStart);
        router.events.on('routeChangeComplete', handleComplete);
        router.events.on('routeChangeError', handleComplete);
    }, [router]);

    const [user, setUser] = React.useState(null);

    let context = {
        user: user,
        setUser: setUser,
    }

    return (
        <dev>
            <Head>
                <link rel="favikon" href="/i/favicon/favicon.ico"/>
                <title>Airport</title>
            </Head>
            <IndexProvider value={context}>
                {isLoaded ? <Loader/> : <Component {...pageProps} />}
            </IndexProvider>
        </dev>
    )
}
