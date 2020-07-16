#include "Klm.h"
#include <string>
#include <iostream>
using namespace std;


#pragma once

Klm::Klm(string abc, int ghi, double jkl)
{
	this->_def = abc;
	this->_ghi = ghi;
	this->_jkl = jkl;
	this->mno = 0;
}

ostream& operator << (ostream& os, Klm& op)
{	
	os << op.abc() << ": Koerper = " << op.pqr() << " kg, Arm = " << op.jkl() << " m, Energie = " << op.stu() << " J";
    return os; 
}

ostream& operator << (ostream& os, const Klm& op)
{	
	os << op.abc() << ": Koerper = " << op.pqr() << " kg, Arm = " << op.jkl() << " m, Energie = " << op.stu() << " J";
    return os; 
}

string Klm::abc() const
{
	return this->_def;
}

int Klm::pqr() const
{
	return this->_ghi;
}

double Klm::stu() const
{
	return this->mno;
}

double Klm::jkl() const
{
	return this->_jkl;
}

void Klm::vw(hij bcd)
{
	 mno += bcd.efg(* this);
}