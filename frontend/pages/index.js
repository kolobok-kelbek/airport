import React from 'react';
import styles from '../styles/Home.module.css'
import {AppBar, Button, Grid, IconButton, Menu, MenuItem, Toolbar, Typography,} from '@material-ui/core'
import {AccountCircle, Menu as MenuIcon, Person, PersonAdd} from '@material-ui/icons'
import Link from 'next/link'
import Logo from '../components/Logo'
import GetCurrentUser from "../http/GetCurrentUser";
import IndexContext from "../context";
import SingOut from "../services/SignOut";
import {useRouter} from "next/router";
import routers from "../http/Routers";

export default function Home() {
    const router = useRouter();
    const indexContext = React.useContext(IndexContext)
    const [anchorEl, setAnchorEl] = React.useState(null);
    const open = Boolean(anchorEl);

    React.useEffect(() => {
        if (indexContext.user === null) {
            GetCurrentUser(data => {
                if (data.error) {
                    router.push(routers.SIGN_IN)
                } else {
                    indexContext.setUser(data)
                }
            })
        }
    }, []);

    const handleMenu = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    return (
        <div>
            <main className={styles.main}>
                <AppBar position="static">
                    <Toolbar>
                        <Grid container spacing={5}>
                            <Grid container item xs={10} direction="row" alignItems="center">
                                <IconButton edge="start" color="inherit" aria-label="menu">
                                    <MenuIcon/>
                                </IconButton>
                                <img style={{margin: '0 15px', borderRadius: "50%"}} width="48px" src="/i/logo.png" alt="Logo"/>
                                <Typography variant="h6">Main page</Typography>
                            </Grid>
                            <Grid container item xs={2} justify="flex-end">
                                <IconButton
                                    aria-label="account of current user"
                                    aria-controls="menu-appbar"
                                    aria-haspopup="true"
                                    onClick={handleMenu}
                                    color="inherit"
                                >
                                    <AccountCircle/>
                                </IconButton>
                                <Menu
                                    id="menu-appbar"
                                    anchorEl={anchorEl}
                                    getContentAnchorEl={null}
                                    anchorOrigin={{
                                        vertical: 'bottom',
                                        horizontal: 'center',
                                    }}
                                    open={open}
                                    onClick={handleClose}
                                >
                                    {indexContext.user === null && <MenuItem>
                                        <PersonAdd style={{marginRight: '5%', color: 'var(--main-color)'}}/>
                                        <Link href={'signup'} style={{marginRight: '10%'}}>Sign Up</Link>
                                    </MenuItem>}
                                    {indexContext.user === null && <MenuItem>
                                        <Person style={{marginRight: '5%', color: 'var(--main-color)'}}/>
                                        <Link href={'signin'} style={{marginRight: '10%'}}>Sign In</Link>
                                    </MenuItem>}
                                    {indexContext.user !== null && <MenuItem>
                                        <Person style={{marginRight: '5%', color: 'var(--main-color)'}}/>
                                        <Button
                                            onClick={() => {
                                                SingOut(router, indexContext)
                                            }}
                                            style={{marginRight: '10%', textTransform: 'none'}}>
                                            Sign Out
                                        </Button>
                                    </MenuItem>}
                                </Menu>
                            </Grid>
                        </Grid>
                    </Toolbar>
                </AppBar>
            </main>
        </div>
    )
}
