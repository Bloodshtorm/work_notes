# Работа с Lets Encrypt

Перевыпуск нового серта

```
sudo certbot certonly --manual --preferred-challenges dns -d bloodshtorm.ru -d *.bloodshtorm.ru
```
> Вывод будет примерно такой:
> 
> Saving debug log to /var/log/letsencrypt/letsencrypt.log
> Requesting a certificate for bloodshtorm.ru and *.> bloodshtorm.ru
> 
> - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
> Please deploy a DNS TXT record under the name:
> 
> _acme-challenge.bloodshtorm.ru.
> 
> with the following value:
> 
> wzn4lFdeTsPp7BFMpAeElaRbvpOTv6J9WUuVyaeEU2s
> 
> Before continuing, verify the TXT record has been deployed. > Depending on the DNS
> provider, this may take some time, from a few seconds to > multiple minutes. You can
> check if it has finished deploying with aid of online tools, > such as the Google
> Admin Toolbox: https://toolbox.googleapps.com/apps/dig/#TXT/> _acme-challenge.bloodshtorm.ru.
> Look for one or more bolded line(s) below the line ';> ANSWER'. It should show the
> value(s) you've just added.
> 
> - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
> Press Enter to Continue
> 
> Successfully received certificate.
> Certificate is saved at: /etc/letsencrypt/live/bloodshtorm.> ru/fullchain.pem
> Key is saved at:         /etc/letsencrypt/live/bloodshtorm.> ru/privkey.pem
> This certificate expires on 2024-09-21.
> These files will be updated when the certificate renews.
> 
> NEXT STEPS:
> - This certificate will not be renewed automatically. > Autorenewal of --manual certificates requires the use of an > authentication hook script (--manual-auth-hook) but one was > not provided. To renew this certificate, repeat this same > certbot command before the certificate's expiry date.
> 
> - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
> If you like Certbot, please consider supporting our work by:
>  * Donating to ISRG / Let's Encrypt:   https://letsencrypt.> org/donate
>  * Donating to EFF:                    https://eff.org/> donate-le
> - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

Так серт можно удалить:
```
 sudo certbot delete --cert-name bloodshtorm.ru
```

Информация по сертификатам:
```
 sudo certbot certificates
```