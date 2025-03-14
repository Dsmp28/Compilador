import { StreamLanguage } from "@codemirror/language";
import { HighlightStyle, syntaxHighlighting } from "@codemirror/language";

import { styleTags, tags as t } from "@lezer/highlight";

const keywords = [
    "entero", "real", "booleano", "caracter", "cadena", // Tipos de datos
    "if", "else", "else if", "while", "for", "do", // Control de flujo
    "escribirlinea", "escribir", "longitud", "acadena" // Funciones predefinidas
];

const myHighlightStyle = HighlightStyle.define([
    { tag: t.keyword, color: "#D87A1E", fontWeight: "bold" }, // Naranja para palabras clave
    { tag: t.comment, color: "#6A9955" }, // Verde para comentarios
    { tag: t.string, color: "#CE9178" }, // Rojo para cadenas
    { tag: t.number, color: "#B5CEA8" }, // Verde claro para números
    { tag: t.function(t.variableName), color: "#569CD6" }, // Azul para funciones
]);

const myLanguage = StreamLanguage.define({
    startState: () => ({}),
    token: (stream) => {
        if(stream.eatSpace()) return null;

        if (stream.match("//")) {
            stream.skipToEnd();
            return "comment";
        }
        if (stream.match(/".*?"/)) {
            return "string";
        }
        if (stream.match(/\b\d+(\.\d+)?\b/)) {
            return "number";
        }
        const word = stream.match(/\b[A-Za-z]+\b/, false);
        if (word && keywords.includes(word[0].toLowerCase())) {
            stream.match(/\b[A-Za-z]+\b/);
            return "keyword";
        }
        stream.next();
        return null;
    },
}); //Hay que cambiar esto quizás

export function myLanguageSupport() {
    return [syntaxHighlighting(myHighlightStyle), myLanguage];
}