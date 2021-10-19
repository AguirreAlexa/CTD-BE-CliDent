import style from '../styles/Home.module.css';
import Navbar from './Navbar';
import {Link} from "react-router-dom";

export default function Home () {

    return(
        <div className={style.bodyHome}>
            <nav className={style.nav}>
                <div className={style.button}>
                    <Link to="/"><Navbar dato="Home" class={style.NavButton}/></Link>
                    <Link to="/about"><Navbar dato="About"class={style.NavButton}/></Link>
                    <Link to="/login"><Navbar dato="Login"class={style.NavButton}/></Link>
                    <Link to="/contact"><Navbar dato="Contact" class={style.NavButton}/></Link>
                </div>
                <h1 className={style.clident}>Cli-Dent</h1>
            </nav>
            <main className={style.main}>
                <article className={style.articleIntro}>
                    <h1 className={style.h1Titulo}>Clínica Odontológica Cli-Dent</h1>
                    <h2 className={style.h2Sub}>Líderes en salud dental</h2>
                    <p>Consulta por el tratamiento que mejor se adapte a tus necesidades!</p>
                    <label for="registrar">
                        <Link to="/pacientes/add"><input name="registrar" type="button" value="Registrate" className={style.registrar}/></Link>
                    </label>
                </article>
            </main>
        </div>
    )
}