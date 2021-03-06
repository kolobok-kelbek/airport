import React from 'react'
import {
    Button,
    Container,
    FormControl,
    Grid,
    IconButton,
    Input,
    InputAdornment,
    InputLabel,
    Link as UiLink
} from '@material-ui/core'
import {Person, Visibility, VisibilityOff} from '@material-ui/icons'
import Link from 'next/link'
import Logo from '../components/Logo'
import "../http/SignIn"
import SignIn from "../http/SignIn";
import Utils from "../http/Utils"
import SignGuard from "../services/SignGuard";
import {useRouter} from "next/router";
import routers from "../http/Routers";

export default () => {
    SignGuard()
    const router = useRouter();

    const TYPE_PASSWORD = 'password';
    const TYPE_TEXT = 'text';

    const [username, setUsername] = React.useState('');
    const [password, setPassword] = React.useState('');
    const [showPassword, setShowPassword] = React.useState(TYPE_PASSWORD);

    const handleChangeUserName = (event) => {
        setUsername(event.target.value);
    };

    const handleChangePassword = (event) => {
        setPassword(event.target.value);
    };

    const handleChangeVisiblePassword = () => {
        setShowPassword(showPassword === TYPE_PASSWORD ? TYPE_TEXT : TYPE_PASSWORD)
    };

    const sendRequest = () => {
        SignIn(username, password, response => {
            if (Utils.isOk(response)) {
                router.push(routers.HOME)
            }
        })
    };

    return (
        <Container maxWidth="xs">
            <Grid
                container
                spacing={0}
                direction="column"
                alignItems="center"
                justify="center"
                style={{minHeight: '100vh'}}
            >
                <Grid container xs={12} justify="center" alignItems="center">
                    <Logo/>
                    <h1 align="center">Sign In </h1>
                    <Person fontSize="large" style={{marginLeft: '5%', color: 'var(--main-color)'}}/>
                </Grid>
                <form noValidate autoComplete="off" style={{marginBottom: '40%'}}>
                    <Grid container spacing={5}>
                        <Grid item xs={12}>
                            <FormControl fullWidth>
                                <InputLabel htmlFor="username">Username</InputLabel>
                                <Input id="username" value={username} onChange={handleChangeUserName}/>
                            </FormControl>
                            <FormControl fullWidth>
                                <InputLabel htmlFor="password">Password</InputLabel>
                                <Input
                                    id="password"
                                    type={showPassword}
                                    value={password}
                                    onChange={handleChangePassword}
                                    endAdornment={
                                        <InputAdornment position="end">
                                            <IconButton
                                                aria-label="toggle password visibility"
                                                onClick={handleChangeVisiblePassword}
                                            >
                                                {showPassword === TYPE_TEXT ? <Visibility/> : <VisibilityOff/>}
                                            </IconButton>
                                        </InputAdornment>
                                    }
                                />
                            </FormControl>
                        </Grid>
                        <Grid container item xs={12} justify="space-between" alignItems="center">
                            <UiLink>
                                <Link href={'signup'}>Sign Up</Link>
                            </UiLink>
                            <Button variant="contained" color="primary" onClick={sendRequest}>Sign in</Button>
                        </Grid>
                    </Grid>
                </form>
            </Grid>
        </Container>
    )
}
