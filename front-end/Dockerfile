#Imagen que se usará para la construcción de la imagen de la aplicación front-end
#Se usa la versión 20.17 de node en alpine3.20
FROM node:20.17-alpine3.20 AS builder
WORKDIR /app

#Copy [Origen] [Destino]
COPY package*.json ./

RUN npm install

COPY . .

RUN npm run build

FROM nginx:alpine

COPY --from=builder /app/dist /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]