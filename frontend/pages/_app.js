import '../styles/globals.css'
import 'fontsource-roboto';
import Head from 'next/head';

function MyApp({ Component, pageProps }) {
  return (
      <dev>
        <Head>
          <link rel="shortcut icon" href="/i/favicon/favicon.ico" />
          <title>Airport</title>
        </Head>
        <Component {...pageProps} />
      </dev>
  )
}

export default MyApp
