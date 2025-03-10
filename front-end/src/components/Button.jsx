const Button = ({ text, onClick, className, icon}) => {
    return (
        <button className={className} onClick={onClick}>
            {icon ? <img src={icon} alt="button icon" className="w-6 h-6" /> : text}
        </button>
    );
};

export default Button;
