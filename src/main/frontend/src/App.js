import Home from './components/Home';
import RegistroPaciente from './components/RegistroPaciente';
import RegistroOdontologo from './components/RegistroOdontologo';
import Pacientes from './components/Pacientes';
import Odontologos from './components/Odontologos';
import Turnos from './components/Turnos';
import Login from './components/Login';
import Admin from './components/Admin';
import {Route, Link, Router, Switch, BrowserRouter} from "react-router-dom";
import IndexPaciente from './components/IndexPaciente';
import ModificarPaciente from './components/ModificarPaciente';
import ModificarOdontologo from './components/ModificarOdontologo';
import RegistroTurnos from './components/RegistroTurnos';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
              <Switch>
                <Route exact path="/" component={Home}/>
                <Route exact path="/pacientes/add" component={RegistroPaciente}/>
                <Route exact path="/login" component={Login}/>
                <Route exact path="/admin" component={Admin}/>
                <Route exact path="/pacientes" component={Pacientes}/>
                <Route exact path="/pacientes/update" component={ModificarPaciente}/>
                <Route exact path="/odontologos" component={Odontologos}/>                  
                <Route exact path="/odontologos/add" component={RegistroOdontologo}/> 
                <Route exact path="/odontologos/update" component={ModificarOdontologo}/> 
                <Route exact path="/turnos" component={Turnos}/> 
                <Route exact path="/turnos/add" component={RegistroTurnos}/>  

                <Route exact path="/index" component={IndexPaciente}/>                  
              </Switch>
      </BrowserRouter>
    </div>
  );
}

export default App;
