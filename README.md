# Java
Create	 Sparse	 matrix	 and	 Sparse	 vectors	 using	 linked	
lists. A	matrix	stores	data	with	2	indices	row	and	column	
(r,c).	Suppose	the	matrix	is	BIG,	say	5000	x	5000	but	only	
100	values	in	it	are	non	zeros,	then	such	matrices	with	
most	of	their	values	as	zeros,	are	called	sparse	matrices.	
We	 need	 a	 list	 that	 finds	 the	 row	 and	 another	 that	
identifies	 a	 column,	 where	 the	 data	 is	 stored	 if	 non	
zero.The	list	will	not	store	row,	column	indices	that	has	
zero	value,	hence	saving	space,	which	is	the	purpose	of	
this	 data	 structure. The	 final sparse matrix	and	vector	
class	will	need	to	implement,	transpose,	multiplyVector
and multiplyMatrix for	the	matrix	class	and	dotProduct
for	the	vector	class,	as	operations	between	them.		The	
matrix	class	should	also	have	a	function	to	slice	rows	or	
columns	from	the	matrix	and	return	as	vectors.
