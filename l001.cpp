#include<iostream>
#include<queue>
#include<vector>
using namespace std;

class TreeNode{
    public:
        int val;
        TreeNode *left;
        TreeNode *right;
        TreeNode() : val(0), left(nullptr), right(nullptr) {}
        TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
        TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

TreeNode *pre;
TreeNode *suc;
TreeNode *last;
void inorder(TreeNode* root,int key){
    if(root==nullptr) return;
    inorder(root->left,key);
    if(root->val==key){
        pre=last;
    } else if(last!=nullptr && last->val==key){
        suc=root;
    }
    last=root;
    inorder(root->right,key);
}

class Pair{
    public:
        TreeNode* node;
        bool isLeft;
        Pair(TreeNode* node,bool isLeft){
            this->node=node;
            this->isLeft=isLeft;
        }
};

int deepestLeftLeaf(TreeNode* root){
    if(root==nullptr) return -1;
    queue<Pair> que;
    que.push(Pair(root,false));
    int ans=-1;
    while(que.size()!=0){
        int size=que.size();
        while (size-->0){
            Pair rpair=que.front();que.pop();
            if(rpair.node->left==nullptr && rpair.node->right==nullptr){
                if(rpair.isLeft){
                    ans=rpair.node->val;
                }
            }
            else{
                if(rpair.node->left!=nullptr) que.push(Pair(rpair.node->left,true));
                if(rpair.node->right!=nullptr) que.push(Pair(rpair.node->right,false));
            }
        }
    }
    return ans;
}

bool isIdentical(vector<int>& arr1,vector<int>& arr2){
    if(arr1.size()!=arr2.size()) return false;
    if(arr1.size()==0) return true;
    if(arr1[0]!=arr2[0]) return false;
    vector<int> small1,more1;
    for(int i=1;i<arr1.size();i++){
        if(arr1[i]>arr1[0]) more1.push_back(arr1[i]);
        else small1.push_back(arr1[i]);
    }
    vector<int> small2,more2;
    for(int i=1;i<arr2.size();i++){
        if(arr2[i]>arr2[0]) more2.push_back(arr2[i]);
        else small2.push_back(arr2[i]);
    }
    return isIdentical(small1,small2) && isIdentical(more1,more2);
}

TreeNode* nextRight(TreeNode* root,int k){
    if(root==nullptr) return nullptr;
    queue<TreeNode*> que;
    que.push(root);
    while(que.size()!=0){
        int size=que.size();
        while(size-->0){
            TreeNode* rnode=que.front();que.pop();
            if(rnode->val==k){
                if(size>0){
                    return que.front();
                } else {
                    return nullptr;
                }
            }
            if(rnode->left!=nullptr) que.push(rnode->left);
            if(rnode->right!=nullptr) que.push(rnode->right);
        }
    }
    return nullptr;
}

void test(TreeNode* root,int k){
    TreeNode* ans=nextRight(root,k);
    if(ans!=nullptr){
        cout << "Next Right of " << k << " is " << ans->val << endl;
    } else {
        cout << "No next right node found for " << k << endl;
    }
}

int main(){
    TreeNode *root = new TreeNode(10);
    root->left = new TreeNode(2);
    root->right = new TreeNode(6);
    root->right->right = new TreeNode(5);
    root->left->left = new TreeNode(8);
    root->left->right = new TreeNode(4);
  
    test(root, 10);
    test(root, 2);
    test(root, 6);
    test(root, 5);
    test(root, 8);
    test(root, 4);
    return 0;
}