FROM public.ecr.aws/nginx/nginx:1.21

RUN rm -rf /etc/nginx/nginx.conf
COPY ./docker/nginx/local/nginx.conf /etc/nginx/nginx.conf

#time zone setting
ENV TZ Asia/Tokyo

EXPOSE 80
