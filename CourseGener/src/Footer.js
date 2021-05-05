import logo from "./images/logo.svg";

function Footer() {
    return (
        <div className='mt-auto bg-dark p-5 text-center'>
            <img src={logo} alt=""></img>
            <h5 className='text-uppercase text-info'>create from a to z</h5>
            <a href='/'><h5>About us</h5></a>
            <a href='/'><h5>What's New?</h5></a>
            <a href='/'><h5>Contacts</h5></a>
            <h6 className='text-muted'>All Rights Reserved</h6>
        </div>
    );
}
export default Footer;