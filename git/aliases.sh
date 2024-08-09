#!/bin/bash

#Выводит некоторое количество последних коммитов
#Короткий хэш - имя коммита - дата - имя коммитера
git-log() {
title "git log"
echo "Press [q] for exit"
git log --pretty=format:"%Cred%h - %C(white)%s - %C(green)%as - %C(yellow)%aN" --since=2.weeks --graph --color | tac | sed 's,/,\\,g' | sed 's,\\,/,g'
}

#Поменять алиасы
alias git-alias-change='nano /etc/profile.d/aliases.sh'

#Перечитать алиасы
alias git-alias-reread='source /etc/profile.d/aliases.sh'

#Сбросить локальные изменения
alias gr='git reset --hard HEAD'

#Спулить изменения
alias gp='git pull'

#Последовательность команд: спулить, git add, и запушить с обозначенным комитом
git-addcpush() {
echo "Последовательность команд: спулить, git add, и запушить с обозначенным комитом"
echo -n "Commit message: "
read COMMIT_MESSAGE
echo ""
git pull && git add --all && git commit -m "$COMMIT_MESSAGE"&& git push && git status -s
}

#Последовательность команд: спулить, git add, и запушить с коммитом autocommit
g-push() {
echo "#Последовательность команд: спулить, git add, и запушить с коммитом autocommit"
git pull && git add --all && git commit -m "autocommit" && git push && git status -s
}

#Декодим base 64
dec64() {
echo -n "Base64 line: "
read LINE64
echo "Result:"
echo $LINE64 | base64 -d
}

#Кодим base 64
enc64() {
echo -n "Line: "
read LINE64
echo "Result:"
echo -n "$LINE64" | Base64 -w 0
}

case "$TERM" in
xterm*)
	# The following programs are known to require a Win32 Console
	# for interactive usage, therefore let's launch them through winpty
	# when run inside `mintty`.
	for name in node ipython php php5 psql python2.7 winget
	do
		case "$(type -p "$name".exe 2>/dev/null)" in
		''|/usr/bin/*) continue;;
		esac
		alias $name="winpty $name.exe"
	done
	;;
esac
