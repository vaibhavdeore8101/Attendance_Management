function branch_detail() {
	var bn = document.branch.bn.value;
	var subname = document.branch.subname.value;
	var sem = document.branch.sem.value;
	var subcode = document.branch.subcode.value;
	if (bn == null || bn == "") {
		alert("Branch can't be blank");
		return false;
	} else if (subname == null) {
		alert("Subject Short Name can't be blank");
		return false;
	} else if (sem == null) {
		alert("Sem can't be blank");
	}else if(subcode==null)
		{
		alert("Subject Code can't be blank")
		}
	return false;
}