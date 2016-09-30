/*
	THIS CODE WORKS ONLY ON UNIX SYSTEM
*/

#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

int fib_end;

void *runner(void *arg); /* the thread */

int main(int argc, char *argv[])
{
	int i;
	fib_end = atoi(argv[1]);

	pthread_t tid; /* the thread identifier */
	pthread_attr_t attr; /* set of attributes for the thread */
	
	if (argc != 2) 
	{
		fprintf(stderr,"usage: a.out <integer value>\n");
		/*exit(1);*/
		return -1;
	}

	if (fib_end < 0) 
	{
		fprintf(stderr,"Argument %d must be non-negative\n", fib_end);
		/*exit(1);*/
		return -1;
	}
	
	int *fib = malloc(fib_end*sizeof(int));

	/* get the default attributes */
	pthread_attr_init(&attr);
	/* create the thread */
	pthread_create(&tid,&attr,runner,fib);
	/* now wait for the thread to exit */
	pthread_join(tid,NULL);
	
	for(i=0; i<fib_end; i++)
	{
		printf("%d\n", fib[i]);
	}
}
	
/**
* The thread will begin control in this function
*/
void *runner(void *fib)
{
	int i;
	
	int *f_fib = fib;
	f_fib[0] = 0;
	f_fib[1] = 1;
	
	for(i=2; i<fib_end; i++)
	{
		f_fib[i] = f_fib[i-1] + f_fib[i-2];
	}
	
	pthread_exit(0);
}
