#include<bits/stdc++.h>
using namespace std;
typedef long long ll;
int main(){
    ll t;
    cin>>t;
    while(t--){
        ll n;
        cin>>n;
        ll a[n];
        ll xr;
        ll cnt=0;
        ll v[1000000][3];
        for(int i=0;i<1000000;i++){v[i][0]=0;v[i][1]=0;}
        int pre[n+1];
        pre[0]=0;
        for(int i=0;i<n;i++)cin>>a[i];
        for(int i=1;i<=n;i++)pre[i]=pre[i-1]^a[i-1];

        for(int i=0;i<=n;i++){
            if(v[pre[i]][0]==0){
                v[pre[i]][0]++;
                v[pre[i]][1]=i;
                v[pre[i]][2]=0;
            }
            else{
                v[pre[i]][2]=v[pre[i]][2]+(i-v[pre[i]][1])*(v[pre[i]][0]);
                v[pre[i]][0]++;
                v[pre[i]][1]=i;
                //cout<<v[pre[i]][0]<<" ";
                cnt+=(v[pre[i]][2]-(v[pre[i]][0]-1));
            }
        }
        //cout<<endl;
        cout<<cnt<<endl;
    }
}
