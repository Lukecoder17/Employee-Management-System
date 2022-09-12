import './App.css';
import {BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import FooterComponent from './components/FooterComponent';
import HeaderComponent from './components/HeaderComponent';
import ListEmployeeComponent from './components/ListEmployeeComponent';

function App() {
  return (
    <div>
      <Router>
      <HeaderComponent />
      <div className="container">
        <Switch>
        <Route exact path = "/" component = {ListEmployeeComponent}></Route>
        <Route path = "/employees" component = {ListEmployeeComponent}></Route>
        </Switch>
     </div>
     <FooterComponent />
     </Router>
    </div>
  );
}

export default App;
