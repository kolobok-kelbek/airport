import React from 'react';
import {Container, FormControl, Input, InputLabel, Grid, InputAdornment, IconButton, Button, Link} from '@material-ui/core'
import {Visibility, VisibilityOff, Person} from '@material-ui/icons';

export default () => {
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
        console.log(showPassword)
        setShowPassword(showPassword === TYPE_PASSWORD ? TYPE_TEXT : TYPE_PASSWORD)
    };

    const sendRequest = () => {
        fetch('http://api.airport.local/auth/signin', {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                username: username,
                password: password,
            }),
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
                style={{ minHeight: '100vh' }}
            >
                <Grid container xs={12} justify="center" alignItems="center">
                    <h1 align="center">Sign In </h1><Person fontSize="large" style={{ marginLeft: '5%', color: 'var(--main-color)' }}/>
                </Grid>
                <form noValidate autoComplete="off" style={{ marginBottom: '40%' }}>
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
                            <Link href={'signup'}>Sign Up</Link>
                            <Button variant="contained" color="primary" onClick={sendRequest}>Sign in</Button>
                        </Grid>
                    </Grid>
                </form>
            </Grid>
        </Container>
    )
}
