import Link from "next/link";
import React from "react";

const SIZE_HIGH = 'high'
const SIZE_MIDDLE = 'middle'
const SIZE_LOW = 'low'

const sizes = new Map([
    [SIZE_HIGH, '128px'],
    [SIZE_MIDDLE, '64px'],
    [SIZE_LOW, '48px'],
])

export default (props) => {
    return  <Link href={'/'}>
        <img style={{marginRight: '15px', cursor: "pointer"}} width={sizes.get(props.size ?? SIZE_MIDDLE)} src="/i/logo_airplain_transparent.png" alt="Logo"/>
    </Link>
}
