import "./bootstrap-4.5.3-dist/css/bootstrap.min.css"
import './App.css';
import {
    BrowserRouter as Router,
    Switch,
    Route
} from "react-router-dom";
import Login from "./Login";
import Register from "./Register";
import Home from "./Home";
import Header from "./Header";
import Profile from "./Profile";
import UserProvider from "./UserProvider";
import Footer from "./Footer";
import TestApi from "./TestApi";


function App() {
    return (
        <UserProvider>
            <div>
                <Router>
                    <Header/>
                    <Switch>
                        <Route path="/login">
                            <Login/>
                        </Route>
                        <Route path='/register'>
                            <Register/>
                        </Route>
                        <Route path='/profile'>
                            <Profile/>
                        </Route>
                        <Route path='/test'>
                            <TestApi/>
                        </Route>
                        <Route path='/'>
                            <Home/>
                        </Route>
                    </Switch>
                    <Footer/>
                </Router>
            </div>
        </UserProvider>
    );
}

export default App;
