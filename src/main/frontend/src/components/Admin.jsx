import style from '../styles/Admin.module.css'
import Navbar from './Navbar';
import {Link} from "react-router-dom";

export default function Admin () {

    return(
        <div className={style.bodyAdmin}>
            <nav className={style.nav}>
                <div className={style.button}>
                    <Link to="/"><Navbar dato="Home" class={style.NavButton}/></Link>
                    <Link to="/pacientes"><Navbar dato="Pacientes"class={style.NavButton}/></Link>
                    <Link to="/odontologos"><Navbar dato="Odontologos"class={style.NavButton}/></Link>
                    <Link to="/turnos"><Navbar dato="Turnos" class={style.NavButton}/></Link>
                </div>
                <h1 className={style.clident}>Cli-Dent</h1>
            </nav>
            <main className={style.main}>
                <article className={style.articleAdmin}>
                    <h1 className={style.h1Titulo}>Bienvenido de nuevo!</h1>
                    <h2 className={style.h2Sub}>Clínica Odontológica Cli-dent</h2>
                    <p></p>
                    <label for="logout">
                        <Link><input name="logout" type="button" value="Logout" className={style.logout}/></Link>
                    </label>
                </article>
            </main>
        </div>
    )
}