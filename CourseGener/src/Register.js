import register1 from "./images/register1.png";
import logo from "./images/logo.svg";
import {Link} from "react-router-dom";
import Footer from "./Footer";

function Register() {
    return (
        <div>
            <div className='registerPage row'>
                <div className='col-7 text-center align-items-center'>
                    <img src={register1} alt=""></img>
                </div>
                <div className='col-5 px-4 text-center'>
                    <img src={logo} alt="" width='50' height='50'></img>
                    <h3 className='my-3'>Create amazing schedule</h3>
                    <h4 className='font-weight-normal'>Create your free account and enjoy using S-GEN</h4>
                    <form>
                        <input type='text' placeholder="Full Name" name='name' className='form-control mt-3'></input>
                        <input type='email' placeholder="Email Address" name='email' className='form-control my-3'></input>
                        <input type='password' placeholder="Password" name='password' className='form-control my-3'></input>
                        <button className='btn btn-primary w-100 btn-lg'>Register</button>
                    </form>
                    <p className='text-muted my-3'>By creating an account, I declare that I have read and
                        accepted S-GEN’s <a href='/' className='underline text-muted'>Terms of Use</a> and
                        <a href='/' className='underline text-muted ml-1'>Privacy Policy</a>.</p>
                    <div className='row offset-4 mb-4'>
                        <p className='text-muted h5 font-weight-normal'>Have an account?</p>
                        <Link to="/login" className='text-info h5 ml-2'>Login</Link>
                    </div>
                </div>
            </div>
            <Footer/>
        </div>
    );
}
export default Register;