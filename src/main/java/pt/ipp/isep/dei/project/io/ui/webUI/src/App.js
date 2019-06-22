import React, {Component} from 'react';
import {HashRouter, Route, Switch} from 'react-router-dom';
import './App.scss';
import PleaseLogin from "./views/Pages/PleaseLogin";

const loading = () => <div className="animated fadeIn pt-3 text-center">Loading...</div>;

// Containers
const DefaultLayout = React.lazy(() => import('./containers/DefaultLayout'));
const Login = React.lazy(() => import('./login/LogInPage.js'));


class App extends Component {

  render() {
    if (localStorage.getItem("loginToken") != null) {
      return (
        <HashRouter>
          <React.Suspense fallback={loading()}>
            <Switch>
              <Route exact path="/about" name="Home" render={props => <DefaultLayout {...props}/>}/>
              <Route exact path="/" name="About" render={props => <DefaultLayout {...props}/>}/>
              <Route path="/" component={DefaultLayout}/>
            </Switch>
          </React.Suspense>
        </HashRouter>
      );
    } else {
      return (
        <HashRouter>
          <React.Suspense fallback={loading()}>
            <Switch>
              <Route exact path="/about" name="Home" render={props => <DefaultLayout {...props}/>}/>
              <Route exact path="/" name="Login" render={props => <Login {...props}/>}/>
              <Route exact path="/pleaseLogIn" name="PleaseLogIn" render={props => <PleaseLogin {...props}/>}/>
              <Route path="/" component={DefaultLayout}/>
            </Switch>
          </React.Suspense>
        </HashRouter>
      );
    }
  }
}

export default App;
