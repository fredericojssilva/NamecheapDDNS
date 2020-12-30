# NamecheapDDNS
NamecheapDDNS is Namecheap Dynamic DNS updater client.
https://www.namecheap.com/support/knowledgebase/article.aspx/29/11/how-do-i-use-a-browser-to-dynamically-update-the-hosts-ip/

# Build
Not going to specify how to build it, just want to mention that shadowJar plugin is intergrated and a fatJar can be genereted executing `gradlew shadowJar` 

# How to use
## Namecheap DDNS setup
https://www.namecheap.com/support/knowledgebase/article.aspx/595/11/how-do-i-enable-dynamic-dns-for-a-domain/
https://www.namecheap.com/support/knowledgebase/article.aspx/43/11/how-do-i-set-up-a-host-for-dynamic-dns/
https://www.namecheap.com/support/knowledgebase/article.aspx/36/11/how-do-i-start-using-dynamic-dns/

## Config
nc_configs.json
```json
{
  "password": "{your_password}",
  "updateTimeInMinutes": 60,
  "entries": [
    {
      "domain": "{your_domain}",
      "host": "{your_host}"
    }
  ]
}
```

## Run
`java -jar NamecheapDDNS-cli-1.0-SNAPSHOT-all.jar`

## Next Steps
1. Create a GUI
