import {Link} from "react-router-dom";
import InputForm from "./InputForm";
import Navbar from "./Navbar";
import style from '../styles/Login.module.css'

export default function Login() {
    return(
        <div className={style.bodyLogin}>
            <nav className={style.nav}>
                <div className={style.button}>
                    <Link to="/"><Navbar dato="Home" class={style.NavButton}/></Link>
                    <Link to="/about"><Navbar dato="About"class={style.NavButton}/></Link>
                    <Link to="/login"><Navbar dato="Login"class={style.NavButton}/></Link>
                    <Link to="/contact"><Navbar dato="Contact" class={style.NavButton}/></Link>
                </div>
                <h1 className={style.clident}>Cli-Dent</h1>
            </nav>
            <main className={style.mainLogin}>
                <article className={style.articleLogin}>
                    <form action=""  className={style.Login}>
                        <h1 className={style.h1Login}>Login</h1>
                        <InputForm dato="Usuario" type="text" />
                        <InputForm dato="Password" type="text" />
                        <p>*Apretar enviar directamente, no tiene funcionalidad*</p>
                        <Link to="/admin"><input type="submit" value="Enviar" className={style.enviar}/></Link>
                    </form>
                </article>
            </main>
        </div>
    )
}