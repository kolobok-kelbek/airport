import React from 'react';
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
import {PersonAdd, Visibility, VisibilityOff} from '@material-ui/icons'
import Link from 'next/link'
import Logo from '../components/Logo'
import SignGuard from "../services/SignGuard";

export default function Home() {
    SignGuard()

    const TYPE_PASSWORD = 'password';
    const TYPE_TEXT = 'text';
    const [username, setUsername] = React.useState('');
    const [password, setPassword] = React.useState('');
    const [email, setEmail] = React.useState('');
    const [firstName, setFirstName] = React.useState('');
    const [lastName, setLastName] = React.useState('');
    const [showPassword, setShowPassword] = React.useState(TYPE_PASSWORD);

    const handleChangeUserName = (event) => {
        setUsername(event.target.value);
    };

    const handleChangeEmail = (event) => {
        setEmail(event.target.value);
    };

    const handleChangePassword = (event) => {
        setPassword(event.target.value);
    };

    const handleChangeFirstName = (event) => {
        setFirstName(event.target.value);
    };

    const handleChangeLastName = (event) => {
        setLastName(event.target.value);
    };

    const handleChangeVisiblePassword = () => {
        setShowPassword(showPassword === TYPE_PASSWORD ? TYPE_TEXT : TYPE_PASSWORD)
    };

    const sendRequest = () => {
        fetch('http://api.airport.local/auth/signup', {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                username: username,
                password: password,
                email: email,
                firstName: firstName,
                lastName: lastName
            }),
        }).then(r => {
            router.push('/')
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
                    <h1 align="center">Sign Up </h1>
                    <PersonAdd fontSize="large" style={{marginLeft: '5%', color: 'var(--main-color)'}}/>
                </Grid>
                <form noValidate autoComplete="off" style={{marginBottom: '40%'}}>
                    <Grid container spacing={5}>
                        <Grid item xs={12}>
                            <FormControl fullWidth>
                                <InputLabel htmlFor="username">Username</InputLabel>
                                <Input id="username" value={username} onChange={handleChangeUserName}/>
                            </FormControl>
                            <FormControl fullWidth>
                                <InputLabel htmlFor="email">Email</InputLabel>
                                <Input id="email" value={email} onChange={handleChangeEmail}/>
                            </FormControl>
                            <FormControl fullWidth>
                                <InputLabel htmlFor="firstName">First name</InputLabel>
                                <Input id="firstName" value={firstName} onChange={handleChangeFirstName}/>
                            </FormControl>
                            <FormControl fullWidth>
                                <InputLabel htmlFor="lastName">Last name</InputLabel>
                                <Input id="lastName" value={lastName} onChange={handleChangeLastName}/>
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
                                <Link href={'signin'}>Sign In</Link>
                            </UiLink>
                            <Button variant="contained" color="primary" onClick={sendRequest}>Sign Up</Button>
                        </Grid>
                    </Grid>
                </form>
            </Grid>
        </Container>
    )
}
