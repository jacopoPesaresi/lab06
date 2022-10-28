# Esercizio di risoluzione di un merge conflict

**Il tempo massimo in laboratorio per questo esercizio è di _20 minuti_.
Se superato, sospendere l'esercizio e riprenderlo per ultimo!**

Si visiti https://github.com/APICe-at-DISI/OOP-git-merge-conflict-test.
Questo repository contiene due branch: `master` e `feature`

Per ognuna delle seguenti istruzioni, si annoti l'output ottenuto.
Prima di eseguire ogni operazione sul worktree o sul repository,
si verifichi lo stato del repository con `git status`.

!!Esercizio però svolto in un'altra cartella, esterna alla repository,
per evitare di inquinare questa nuova repository

1. Si cloni localmente il repository
'''
$git clone https://github.com/APICe-at-DISI/OOP-git-merge-conflict-test.git
$ cd OOP-git-merge-conflict-test/
$ git status
On branch master
Your branch is up to date with 'origin/master'.

nothing to commit, working tree clean
'''

2. Ci si assicuri di avere localmente entrambi i branch remoti
'''
$ git branch -v
* master 8e0f29c Change HelloWorld to print the number of available processors

$ git checkout -b feature origin/feature
Switched to a new branch 'feature'
Branch 'feature' set up to track remote branch 'feature' from 'origin'.

$ git branch -v
* feature bed943f Print author information
  master  8e0f29c Change HelloWorld to print the number of available processors

$ git status
On branch feature
Your branch is up to date with 'origin/feature'.

nothing to commit, working tree clean
'''

3. Si faccia il merge di `feature` dentro `master`, ossia: si posizioni la `HEAD` su `master`
   e da qui si esegua il merge di `feature`
'''
$ git status
On branch feature
Your branch is up to date with 'origin/feature'.

nothing to commit, working tree clean

$ git checkout master 
Switched to branch 'master'
Your branch is up to date with 'origin/master'.

$ git merge feature
Auto-merging HelloWorld.java
CONFLICT (content): Merge conflict in HelloWorld.java
Automatic merge failed; fix conflicts and then commit the result.

'''

4. Si noti che viene generato un **merge conflict**!
'''

'''

5. Si risolva il merge conflict come segue:
   - Il programma Java risultante deve stampare sia il numero di processori disponibili
     (funzionalità presente su `master`)
     che il nome dell'autore del file
     (funzionalità presente su `feature`)
'''
Mantenute quindi entrambe le modifiche con l'editor online

$ git status
On branch master
Your branch is up to date with 'origin/master'.

You have unmerged paths.
  (fix conflicts and run "git commit")
  (use "git merge --abort" to abort the merge)

Unmerged paths:
  (use "git add <file>..." to mark resolution)
        both modified:   HelloWorld.java

$ git add HelloWorld.java 

$ git commit -m "Executed merge between branch feature into master"
[master 2cea8b0] Executed merge between branch feature into master
'''

6. Si crei un nuovo repository nel proprio github personale
'''

'''

7. Si aggiunga il nuovo repository creato come **remote** e si elenchino i remote
'''
$ git remote add mine https://github.com/jacopoPesaresi/gitEserciceOfLab06.git

$ git remote -v
mine    https://github.com/jacopoPesaresi/gitEserciceOfLab06.git (fetch)
mine    https://github.com/jacopoPesaresi/gitEserciceOfLab06.git (push)
origin  https://github.com/APICe-at-DISI/OOP-git-merge-conflict-test.git (fetch)
origin  https://github.com/APICe-at-DISI/OOP-git-merge-conflict-test.git (push)

'''

8. Si faccia push del branch `master` sul proprio repository
'''
$ git push mine master
Enumerating objects: 15, done.
Counting objects: 100% (15/15), done.
Delta compression using up to 8 threads
Compressing objects: 100% (11/11), done.
Writing objects: 100% (15/15), 1.59 KiB | 1.59 MiB/s, done.
Total 15 (delta 4), reused 10 (delta 2), pack-reused 0
remote: Resolving deltas: 100% (4/4), done.
To https://github.com/jacopoPesaresi/gitEserciceOfLab06.git
 * [new branch]      master -> master
'''

9. Si setti il branch remoto `master` del nuovo repository come *upstream* per il proprio branch `master` locale
'''
$ git branch --set-upstream-to=mine/master
Branch 'master' set up to track remote branch 'master' from 'min
'''

