export default function InputForm(props){
    return(
        <>
        <label for={props.dato}>{props.dato}</label>
        <input id={props.dato} name={props.name} type={props.type} onChange={props.event} className={props.tag}/>
        </>
    )
}