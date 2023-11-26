#!/bin/bash

install_if_not_exist() {
    if dpkg -s "$1" &>/dev/null; then
        PKG_EXIST=$(dpkg -s "$1" | grep "install ok installed")
        if [[ -n "$PKG_EXIST" ]]; then
            echo "$1" "Already installed"
            return
        fi
    fi
    sudo apt install "$1" -y
}

install_if_not_exist apache2-utils
sudo apt install apache2-utils
sudo mkdir -p /etc/grid-router/quota
sudo cp ./selenoid/balancing_rules/test.xml /etc/grid-router/quota
htpasswd -bc /etc/grid-router/users.htpasswd test test-password

for each in {"selenoid/chrome:119.0","selenoid/chrome:118.0","selenoid/opera:91.0","selenoid/opera:90.0"};
do docker pull $each;
done

docker compose -f docker-compose.yml up -d
